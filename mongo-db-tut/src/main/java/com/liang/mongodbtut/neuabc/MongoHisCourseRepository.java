package com.liang.mongodbtut.neuabc;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoHisCourseRepository extends MongoRepository<HisCourseDto,String> {


}
