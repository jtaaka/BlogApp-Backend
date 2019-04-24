package fi.tuni.blogapp.blogapp.restcontroller;

import fi.tuni.blogapp.blogapp.model.Blogpost;
import fi.tuni.blogapp.blogapp.model.Comment;
import fi.tuni.blogapp.blogapp.repository.BlogpostRepository;
import fi.tuni.blogapp.blogapp.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

/**
 * Rest controller for blogpost database.
 */
@RestController
public class BlogpostRestController {

    /**
     * Blogpost database.
     */
    @Autowired
    BlogpostRepository database;

    /**
     * Comment database.
     */
    @Autowired
    private CommentRepository commentRepository;

    /**
     * Method for fetching blogposts by id.
     *
     * @param id Used to input blogpost id.
     * @return Blogpost by inputted id.
     * @throws SomethingWentWrongException Thrown when no post with the inputted id.
     */
    @RequestMapping(value = "/posts/{id}",  method= RequestMethod.GET)
    public ResponseEntity<Blogpost> fetchPost(@PathVariable Long id) throws SomethingWentWrongException {
        Optional<Blogpost> location = database.findById(id);
        if (location.isPresent())
            return new ResponseEntity<>(location.get(), HttpStatus.OK);
        else
            throw new SomethingWentWrongException("not found");
    }

    /**
     * Method for fetching all blogposts.
     *
     * @return All of blogposts.
     */
    @RequestMapping(value = "/posts",  method= RequestMethod.GET)
    public Iterable<Blogpost> fetchAll() {
        return database.findAll();
    }

    /**
     * Method for deleting blogpost by id.
     *
     * @param id Used to input id.
     * @return OK status.
     * @throws SomethingWentWrongException Indicates failure on delete process.
     */
    @RequestMapping(value = "/posts/{id}",  method= RequestMethod.DELETE)
    public ResponseEntity<Void> deletePost(@PathVariable Long id) throws SomethingWentWrongException {
        if (database.existsById(id)) {
            database.deleteById(id);
            List<Comment> comments = commentRepository.findAllByPostId(id);

            if (comments.size() > 0) {
                commentRepository.deleteAll(comments);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new SomethingWentWrongException("delete failed");
        }
    }

    /**
     * Method for saving blogpost.
     *
     * @param post Used to input post.
     * @param b Uri builder.
     * @return Saved post.
     */
    @RequestMapping(value = "/posts",  method= RequestMethod.POST)
    public ResponseEntity<Blogpost> save(@RequestBody Blogpost post, UriComponentsBuilder b) {
        database.save(post);

        UriComponents uriComponents =
                b.path("/posts/{id}").buildAndExpand(post.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(post, headers, HttpStatus.CREATED);
    }

    /**
     * Method for updating blogpost.
     *
     * @param id Used to input id.
     * @param post Used to input blogpost.
     * @return null.
     */
    @RequestMapping(value = "/posts/{id}", method= RequestMethod.PUT)
    public Blogpost update(@PathVariable Long id, @RequestBody Blogpost post) {
        if (database.existsById(id)) {
            Blogpost blogpost = database.findById(id).get();
            blogpost.setContent(post.getContent());
            blogpost.setTitle(post.getTitle());
            database.save(blogpost);
        }

        return null;
    }

    /**
     * Exception class for failure cases.
     */
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    private class SomethingWentWrongException extends IllegalArgumentException {

        /**
         * Method for exception message.
         *
         * @param msg Used to input message.
         */
        private SomethingWentWrongException(String msg) {
            super(msg);
        }
    }
}
