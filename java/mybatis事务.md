# mybatis事务学习
mybatis本身执行完语句必须主动commit，否则，是不生效的。
通过一个demo来说这个问题
首先我们需要在数据库中创建一个User表。语句如下：
```sql
CREATE TABLE User (
    id INTEGER not null primary key auto_increment,
    userName varchar(30) not null,
    password varchar(30) not null,
    birthday Datetime not null
);
```
接下来，我们需要创建对应的是实体类User.java
```java
package com.study.mybatis.user.bean;

import java.util.Date;

public class User {
	private int id;
	private String userName;
	private String password;
	private Date birthday;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String uerName) {
		this.userName = uerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
```
接下来，我们需要配置一些关于数据库的连接配置，配置文件放在config/mybatis-config.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <settings>
      <setting name="cacheEnabled" value="false"/>
    </settings>
    <!-- 对事务的管理和连接池的配置 -->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC" />
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver" />
                <property name="url" value="jdbc:mysql://localhost:3306/sampledb" />
                <property name="username" value="root" />
                <property name="password" value="1234" />
            </dataSource>
        </environment>
    </environments>

    <!-- mapping 文件路径配置 -->
    <mappers>
        <mapper resource="config/UserDao.xml" />
    </mappers>
</configuration>
```
然后定义UserDao.xml文件
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.study.mybatis.user.dao.IUserDao" >

  <!-- 根据用户名查找用户 -->
  <select id="selectUserbyName" resultType="com.study.mybatis.user.bean.User" parameterType="java.lang.String" >
    select *
    from User
    where userName = #{userName,jdbcType=VARCHAR}
  </select>

  <!-- 根据用户名和密码查找用户 -->
  <select id="selectUser" resultType="com.study.mybatis.user.bean.User" >
    select *
    from User
    where userName = #{0,jdbcType=VARCHAR} AND password = #{1,jdbcType=VARCHAR}
  </select>

  <!-- 删除用户 -->
  <delete id="deleteUser" parameterType="com.study.mybatis.user.bean.User" >
    delete from user
    where id = #{id,jdbcType=INTEGER}
  </delete>

  <!-- 添加用户 -->
  <insert id="addUser" parameterType="com.study.mybatis.user.bean.User" >
    insert into user (userName, password, birthday)
    values (#{userName,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, #{birthday,jdbcType=TIMESTAMP}
      )
  </insert>
  <!-- 用户信息修改 -->
  <update id="updateUser" parameterType="com.study.mybatis.user.bean.User" >
    update user
    set userName = #{userName,jdbcType=VARCHAR},
      password = #{password,jdbcType=VARCHAR},
      birthday = #{birthday,jdbcType=TIMESTAMP}
    where id = #{id,jdbcType=INTEGER}
  </update>
</mapper>
```
接下来，我们需要定义相关接口IUserDao.java
```java
package com.study.mybatis.user.dao;

import com.study.mybatis.user.bean.User;

public interface IUserDao {

	void addUser(User user);

	User selectUserbyName(String userName);

	User selectUser(String userName, String password);

	void updateUser(User user);

	void deleteUser(User user);
}

```
实现一个测试用例来验证我们相关的说法
```java
package com.study.mybatis.user.dao;

import static org.junit.Assert.assertEquals;

import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.study.mybatis.user.bean.User;

public class DaoTest {

    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static{
        try{
        	//加载关于mybatis相关的配置
            reader    = Resources.getResourceAsReader("config/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }
    @Test
    public void test() throws InterruptedException {
        SqlSession session = sqlSessionFactory.openSession();
           try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date birthday = sdf.parse("1990-03-01 18:30:00");
            IUserDao userDao = session.getMapper(IUserDao.class);
            User user = new User();
            user.setUserName("mathyrs2");
            user.setPassword("123");
            user.setBirthday(birthday);
            userDao.addUser(user);
            //插入操作必须提交
//            session.commit();
           } catch (ParseException e) {
            e.printStackTrace();
        } finally {
               session.close();
           }
    }
}
```
虽然，我们执行过程中，并没有任何报错。但是，在数据库中，我们并没有查询到该条记录。
但是如果我们将注释语句session.commit();放开，则会再数据库中正常查询到该条数据的。
表明mybatis自身是不会主动提交，必须我们主动提交，才会生效。
