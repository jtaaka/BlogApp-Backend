package fi.tuni.blogapp.blogapp.restcontroller;

import fi.tuni.blogapp.blogapp.model.Comment;
import fi.tuni.blogapp.blogapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
public class CommentRestController {

    @Autowired
    CommentRepository database;

    @RequestMapping(value = "/comment/{id}",  method= RequestMethod.GET)
    public ResponseEntity<Comment> fetchComment(@PathVariable Long id) throws SomethingWentWrongException {
        Optional<Comment> comment = database.findById(id);
        if (comment.isPresent())
            return new ResponseEntity<>(comment.get(), HttpStatus.OK);
        else
            throw new SomethingWentWrongException("not found");
    }

    @RequestMapping(value = "/comments",  method= RequestMethod.GET)
    public Iterable<Comment> fetchAll() {
        return database.findAll();
    }

    @RequestMapping(value = "/comment/{id}",  method= RequestMethod.DELETE)
    public ResponseEntity<Void> deletePost(@PathVariable Long id) throws SomethingWentWrongException {
        if (database.existsById(id)) {
            database.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new SomethingWentWrongException("delete failed");
        }
    }

    @RequestMapping(value = "/comment",  method= RequestMethod.POST)
    public ResponseEntity<Comment> save(@RequestBody Comment comment, UriComponentsBuilder b) {
        database.save(comment);

        UriComponents uriComponents =
                b.path("/comment/{id}").buildAndExpand(comment.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(comment, headers, HttpStatus.CREATED);
    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    private class SomethingWentWrongException extends IllegalArgumentException {
        private SomethingWentWrongException(String msg) {
            super(msg);
        }
    }
}
