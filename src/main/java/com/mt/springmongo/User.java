// package com.mt.springmongo;

// import java.io.Serializable;
// import java.util.Objects;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;

// /**
//  * Represents a User which is mapped to <code>users</code> collection in MongoDB
//  *
//  * @author Mithun
//  */
// @Document(collection = "users")
// public class User implements Serializable {

//     /**
// 	 * 
// 	 */
// 	private static final long serialVersionUID = 1L;
	
// 	@Id
//     private String id;
//     private String firstName;
//     private String lastName;
//     private String email;

//     public User(String firstName, String lastName, String email) {
//         this.firstName = firstName;
//         this.lastName = lastName;
//         this.email = email;
//     }

//     public String getId() {
//         return id;
//     }

//     public void setId(String id) {
//         this.id = id;
//     }

//     public String getFirstName() {
//         return firstName;
//     }

//     public void setFirstName(String firstName) {
//         this.firstName = firstName;
//     }

//     public String getLastName() {
//         return lastName;
//     }

//     public void setLastName(String lastName) {
//         this.lastName = lastName;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     @Override
//     public boolean equals(Object o) {
//         if (this == o) return true;
//         if (o == null || getClass() != o.getClass()) return false;
//         User user = (User) o;
//         return Objects.equals(id, user.id) &&
//                 Objects.equals(firstName, user.firstName) &&
//                 Objects.equals(lastName, user.lastName) &&
//                 Objects.equals(email, user.email);
//     }

//     @Override
//     public int hashCode() {
//         return Objects.hash(id, firstName, lastName, email);
//     }
// }


package com.mt.springmongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


 * Controller which handles reqest for saving {@link User}s.
/**
 * @author 
 */
@Controller
public class UserController {
    private final UserRepository userRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/save")
    public String save(@RequestParam("firstName") String firstName,
                       @RequestParam("lastName") String lastName,
                       @RequestParam("email") String email) {

    	logger.info("Creating user name: "+firstName);
        User user = new User(firstName, lastName, email);
        userRepository.save(user);

        return "redirect:/";
    }
}
