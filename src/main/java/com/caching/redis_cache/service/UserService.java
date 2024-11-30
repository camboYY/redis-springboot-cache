package com.caching.redis_cache.service;

import com.caching.redis_cache.entity.User;
import com.caching.redis_cache.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Cacheable(key = "#id", value = "users")
    public User findUser(Long id) {
        System.out.println(id);
        return this.repository.findById(id).orElse(null);
    }

    @CachePut(value = "users", key = "#user.id")
    public User createUser(User user){
        return this.repository.save(user);
    }

    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        this.repository.deleteById(id);
    }
}


