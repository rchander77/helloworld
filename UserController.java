package com.example.helloworld;

import com.example.helloworld.request.UserModel;
import com.example.helloworld.request.UserQueryModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();

//    @GetMapping
//    public User loginUser(UserQueryModel userQueryModel) {
//        User user = modelMapper.map(userQueryModel, User.class);
//        User existingUser = userRepository.findByUserName(user.getUserName());
//        if (ObjectUtils.isEmpty(existingUser)) {
//            throw new NotFoundException("No user exists with given search criteria.", "USER_NOT_FOUND_EXCEPTION", null);
//        }
//        return existingUser;
//    }

    @GetMapping
    public List<User> getUsers(UserQueryModel userQueryModel) {
        User user = modelMapper.map(userQueryModel, User.class);
        Example userExample = Example.of(user);
        List<User> users = userRepository.findAll(userExample);
        return users;
    }

    @PostMapping()
    public UserModel registerUser(@RequestBody UserModel createUserModel) {
        User userRequest = modelMapper.map(createUserModel, User.class);
        validateUserAlreadyExists(userRequest);
        User user = userRepository.save(userRequest);
        return modelMapper.map(user, UserModel.class);
    }

    @PatchMapping(value = "/{id}")
    public UserModel patchUser(@PathVariable(value = "id") Long id, @RequestBody String jsonPatch) throws ApplicationException {
        User user = returnUserIfExists(id);
        user = ApiUtils.patch(user, jsonPatch, User.class);
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserModel.class);
    }

    private User returnUserIfExists(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (ObjectUtils.isEmpty(user)) {
            throw new NotFoundException("No user exists with given search criteria.", "USER_NOT_FOUND_EXCEPTION", null);
        }
        return user;
    }

    private void validateUserAlreadyExists(User user) {
        User existingUser = userRepository.findByUserName(user.getUserName());
        if (!ObjectUtils.isEmpty(existingUser)) {
            throw new BadRequestException(String.format("An user already exists for the provided user name: %s", user.getUserName()), "USER_ALREADY_EXISTS", null);
        }
    }
}
