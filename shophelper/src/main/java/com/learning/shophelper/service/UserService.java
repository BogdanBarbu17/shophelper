package com.learning.shophelper.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.shophelper.api.model.UserDto;
import com.learning.shophelper.data.access.model.RoleEntity;
import com.learning.shophelper.data.access.model.UserEntity;
import com.learning.shophelper.data.access.repository.RoleRepository;
import com.learning.shophelper.data.access.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserDto getUserById(Long id){
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        userEntity.setPassword(null);
        return toDto(userEntity);
    }

    public UserDto createUser(UserDto user) {
        UserEntity newUser = toEntity(user);
        RoleEntity roleEntity = roleRepository.findById(user.getRoleId()).orElseThrow();
        newUser.setRole(roleEntity);
        newUser = userRepository.save(newUser);
        newUser.setPassword(null);
        return toDto(newUser);
    }

    public UserDto updateUser(UserDto user, Long id ){
        UserEntity newUser = toEntity(user);
        RoleEntity roleEntity = roleRepository.findById(user.getRoleId()).orElseThrow();
        newUser.setRole(roleEntity);
        UserEntity oldUser = userRepository.findById(id).orElseThrow();
        newUser.setId(oldUser.getId());
        newUser = userRepository.save(newUser);
        return toDto(newUser);
    }

    public List<UserDto> getAllUsers(){
        List<UserDto> userDtos =new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        for (UserEntity userEntity : userEntities){
            userDtos.add( toDto(userEntity));
        }
        return userDtos ;
    }

    public void deleteUser (Long id){
        userRepository.deleteById(id);
    }

    private UserEntity toEntity(UserDto userDto) {
        return objectMapper.convertValue(userDto, UserEntity.class);
    }

    private UserDto toDto(UserEntity userEntity) {
        UserDto userDto = objectMapper.convertValue(userEntity, UserDto.class);
        userDto.setRoleId(userEntity.getRole().getId());
        return userDto;
    }

}
