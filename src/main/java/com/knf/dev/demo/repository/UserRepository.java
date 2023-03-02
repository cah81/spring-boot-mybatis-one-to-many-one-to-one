package com.knf.dev.demo.repository;

import com.knf.dev.demo.model.Card;
import com.knf.dev.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRepository {
 
    /*
     *  one to many Select.
     */
    @Select("SELECT users.name, users.email " +
            "FROM users WHERE users.email = #{email}")
    @Results(value = {
         @Result(property = "email", column = "email"),
         @Result(property="cards", javaType=List.class, column="email",
         many=@Many(select="getCards"))
                      })
    User selectUserById(String email);

    @Select("SELECT cards.id, cards.cardnumber, cards.cardtype, " +
            "cards.email FROM cards WHERE cards.email = #{email}")
    @Results(value = {
            @Result(property = "cardNumber", column = "cardnumber"),
            @Result(property = "cardType", column = "cardtype")
    })
    List<Card> getCards(String email);

    @Insert("INSERT INTO users(email,name)" +
            "VALUES(#{email},#{name})")
    int insert(User user);

    @Update("update users set email=#{email},  " +
            "name=#{name}, where id =#{id}"
    )
    int update(User user);


    @Select("SELECT * from users WHERE id = #{id}")
    @Results({
            @Result(property = "firstName",column = "first_name"),
            @Result(property = "lastName",column = "last_name"),
            @Result(property = "emailId",column = "email_id")
    })
    Optional<User> findById(Integer id);




}