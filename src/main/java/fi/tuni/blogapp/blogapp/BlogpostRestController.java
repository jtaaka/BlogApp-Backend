package fi.tuni.blogapp.blogapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
public class BlogpostRestController {

    @Autowired
    BlogpostRepository database;

    @RequestMapping(value = "/posts/{id}",  method= RequestMethod.GET)
    public ResponseEntity<Blogpost> fetchLocation(@PathVariable long id) throws SomethingWentWrongException {
        Optional<Blogpost> location = database.findById(id);
        if (location.isPresent())
            return new ResponseEntity<Blogpost>(location.get(), HttpStatus.OK);
        else
            throw new SomethingWentWrongException("not found");
    }

    @RequestMapping(value = "/posts",  method= RequestMethod.GET)
    public Iterable<Blogpost> fetchAll() {
        return database.findAll();
    }

    @RequestMapping(value = "/posts/{id}",  method= RequestMethod.DELETE)
    public ResponseEntity<Void> deleteLocation(@PathVariable long id) throws SomethingWentWrongException {
        if (database.existsById(id)) {
            database.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            throw new SomethingWentWrongException("delete failed");
        }
    }

    @RequestMapping(value = "/posts",  method= RequestMethod.POST)
    public  ResponseEntity<Blogpost> save(@RequestBody Blogpost post, UriComponentsBuilder b) {
        database.save(post);

        UriComponents uriComponents =
                b.path("/posts/{id}").buildAndExpand(post.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<Blogpost>(post, headers, HttpStatus.CREATED);
    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    class SomethingWentWrongException extends IllegalArgumentException {
        public SomethingWentWrongException(String msg) {
            super(msg);
        }
    }
}
