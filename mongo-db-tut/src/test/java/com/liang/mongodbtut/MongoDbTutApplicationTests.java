package com.liang.mongodbtut;

import com.alibaba.fastjson.JSON;
import com.liang.mongodbtut.neuabc.HisCourseMsgDto;
import com.liang.mongodbtut.neuabc.ImportService;
import com.liang.mongodbtut.neuabc.MongoHisCourseRepository;
import com.liang.mongodbtut.test.*;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDbTutApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDb(){

        jdbcTemplate.execute("select count(1) from dual");


    }


    @Autowired
    private UserRepository userRepository;

    @Test
    public void testMongo(){
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("liang");
        user.setPassword("123456");

        List<Course> courseList = new ArrayList<>();

        Course course1 = new Course();

        course1.setId(UUID.randomUUID().toString());
        course1.setCourseName("aaa");
        course1.setLevel("l1");
        course1.setCourseTime(new Date());

        Course course2 = new Course();

        course2.setId(UUID.randomUUID().toString());
        course2.setCourseName("bbbb");
        course2.setLevel("l2");
        course2.setCourseTime(new Date());


        courseList.add(course1);
        courseList.add(course2);

        user.setCourseList(courseList);

        userRepository.save(user);

        List<User> list  = userRepository.findAll();


        list.stream().forEach(System.out::println);


    }

    @Test
    public void find(){

        User user = new User();
        user.setId("7811b9be-9f2b-4b4c-bffb-d1bade809c9b");
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact());

        Example<User> example = Example.of(user, matcher);


        List<User> list = userRepository.findAll(example);

        list.stream().forEach(System.out::println);

    }


    @Test
    public void update(){

        User user = new User();
        user.setId("7811b9be-9f2b-4b4c-bffb-d1bade809c9b");
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact());

        Example<User> example = Example.of(user, matcher);

        User userDb = userRepository.findOne(example).orElse(null);

        if(userDb!=null){
            Course course = new Course();
            course.setId(UUID.randomUUID().toString());
            course.setCourseName("cccccc");
            course.setLevel("l4");
            course.setCourseTime(new Date());

            userDb.getCourseList().add(course);

            userRepository.save(userDb);

        }

        List<User> list  = userRepository.findAll();


        list.stream().forEach(System.out::println);
    }

    @Test
    public void testCourseTimeFind(){

        List<User> list = userRepository.find("l4");

        list.stream().forEach(System.out::println);


        list = userRepository.findByCourseTime("2020-02-15 00:00:00","2020-02-15 23:59:59");

        list.stream().forEach(System.out::println);

    }



    @Autowired
    private ImportService importService;


    @Test
    public void importData(){

        importService.getAllCourse();
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testDate() throws ParseException {

        String startTime  = "2020-02-01 00:00:00";
        String endTime = "2020-02-01 23:59:59";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Query query =new Query();

        Criteria criteria = new Criteria();

        //criteria.where("1=1 ");

        //精确匹配
        //criteria.and("only_id").is("762f8977dae3434bb42358b3fb2fb0d8");

        //criteria.and("user_id").is("e1a133b94c694e59ad716fdc64243571s");

        //criteria.and("real_name").regex(".*?" +"菲"+ ".*");


        //模糊查询
        Criteria criteria1 = new Criteria().orOperator(
                Criteria.where("nickname").regex(".*?" +"菲"+ ".*"),
                Criteria.where("real_name").regex(".*?" +"梁"+ ".*")
        );

        //时间范围查询
//        Criteria sub = Criteria.where("course_time");
//
//        if(!StringUtils.isEmpty(startTime)){
//            sub = sub.gte(simpleDateFormat.parse(startTime));
//        }
//
//        if(!StringUtils.isEmpty(endTime)){
//            sub = sub.lte(simpleDateFormat.parse(endTime));
//        }
//
//        query.addCriteria(sub);


        //如果是同一个条件的话就得写成andOperator这种，如果是单条件，两种写法都可以
        //还是上面你的写法比较好
        Criteria criteria2 = new Criteria().andOperator(
                Criteria.where("course_time").gt(simpleDateFormat.parse(startTime)),
                Criteria.where("course_time").lt(simpleDateFormat.parse(endTime))
        );

        criteria.andOperator(criteria1,criteria2);

        query.addCriteria(criteria);

        List<HisCourseMsgDto> list = mongoTemplate.find(query,HisCourseMsgDto.class);

        System.out.println(JSON.toJSONString(list));
    }


    @Test
    public void testComplexQuery() throws Exception{
        String startTime  = "2020-02-01 00:00:00";
        String endTime = "2020-02-01 23:59:59";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Query query =new Query();

        Criteria criteria = new Criteria();

        //模糊查询
        Criteria criteria1 = new Criteria().orOperator(
                Criteria.where("nickname").regex(".*?" +"菲"+ ".*")
                ,Criteria.where("real_name").regex(".*?" +"菲"+ ".*")
        );


        Criteria criteria2 = new Criteria().andOperator(
                Criteria.where("course_time").gte(simpleDateFormat.parse(startTime)),
                Criteria.where("course_time").lte(simpleDateFormat.parse(endTime))

        );

        criteria.andOperator(criteria1,criteria2);

        query.addCriteria(criteria);

        List<HisCourseMsgDto> list = mongoTemplate.find(query,HisCourseMsgDto.class);

        System.out.println(JSON.toJSONString(list));

    }


    @Test
    public void insertDemo() throws Exception{

        String startTime  = "2020-02-01 01:00:00";
        String endTime = "2020-02-01 22:59:59";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setCourse_time(simpleDateFormat.parse(startTime));
        demoEntity.setNickname("菲菲");
        demoEntity.setReal_name("飞飞飞");


        mongoTemplate.insert(demoEntity);

        demoEntity = new DemoEntity();
        demoEntity.setCourse_time(simpleDateFormat.parse(endTime));
        demoEntity.setReal_name("梁456");
        demoEntity.setNickname("菲菲");

        mongoTemplate.insert(demoEntity);


        demoEntity=new DemoEntity();

        demoEntity.setCourse_time(simpleDateFormat.parse("2020-01-01 00:40:40"));
        demoEntity.setNickname("菲菲");
        demoEntity.setReal_name("梁123");
        mongoTemplate.insert(demoEntity);


    }


    @Test
    public void testComplexQueryDemo() throws Exception{
        String startTime  = "2020-02-01 00:00:00";
        String endTime = "2020-02-01 23:59:59";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Query query =new Query();

        Criteria criteria = new Criteria();

        //模糊查询
        Criteria criteria1 = new Criteria().orOperator(
                Criteria.where("nickname").regex(".*?" +"aaa"+ ".*")
                ,Criteria.where("real_name").regex(".*?" +"梁"+ ".*")
        );


        Criteria criteria2 = new Criteria().andOperator(
                Criteria.where("course_time").gte(simpleDateFormat.parse(startTime)),
                Criteria.where("course_time").lte(simpleDateFormat.parse(endTime))

        );

        criteria.andOperator(criteria1,criteria2);

        query.addCriteria(criteria);

        List<DemoEntity2> list = mongoTemplate.find(query, DemoEntity2.class);

        System.out.println(JSON.toJSONString(list));

    }
}
