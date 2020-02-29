package com.liang.mongodbtut.neuabc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ImportService {



    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;


    public void getAllCourse(){

        System.out.println("begin...");

        String sql = "select * from table_all_his";

        List<HisCourseDto> allCourse = jdbcTemplate.query(sql,new HisCourseDtoRowMapper());



        System.out.println(allCourse.size());

        System.out.println("query finish");

        mongoTemplate.dropCollection("his_course");
        for (HisCourseDto hisCourseDto:allCourse){
            mongoTemplate.insert(hisCourseDto);
        }

        System.out.println("import finish");

    }


}


class HisCourseDtoRowMapper implements RowMapper<HisCourseDto> {

    @Override
    public HisCourseDto mapRow(ResultSet resultSet, int i) throws SQLException {
        HisCourseDto hisCourseDto = new HisCourseDto();

        Field[] fields = HisCourseDto.class.getDeclaredFields();
        for (Field field:fields){
            field.setAccessible(true);

            if(field.isAnnotationPresent(Column.class)){

                Column column = field.getAnnotation(Column.class);

                String columnName = column.name();

                try {
                    field.set(hisCourseDto,resultSet.getObject(columnName));
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }

            }
        }

        //System.out.println(marketingCollectDataInfo);
        return hisCourseDto;
    }
}
