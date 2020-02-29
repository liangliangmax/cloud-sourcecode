package com.liang.mongodbtut.test;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    @Query(value = "{'courseList.level':'?0'}")
    List<User> find(String level);


    @Query(value = "{'courseList.courseTime': {$gte: ?0, $lte:?1 }}")
    List<User> findByCourseTime(String courseTimeStart,String courseTimeEnd);
}
