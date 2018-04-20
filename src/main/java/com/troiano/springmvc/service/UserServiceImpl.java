package com.troiano.springmvc.service;

import com.troiano.springmvc.model.User;
import com.troiano.springmvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentService documentService;

    public List<User> findAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public void deleteUser(int idU){
        User user = userRepository.findOne(idU);

        if(user.getUserDocuments() != null)
            documentService.deleteById(user.getUserDocuments().getIdUD());
        userRepository.delete(idU);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    @Override
    public User findById(int idU) {
        return userRepository.findOne(idU);
    }

    @Override
    public void updateUser(User user) {
       userRepository.save(user);
    }

    @Override
    public List<User> search(String str) {
        return userRepository.findByFirstnameContainingOrLastnameContainingOrCountryContaining(str, str, str);
    }
}
