package com.knf.dev.demo.repository;

import com.knf.dev.demo.model.Card;
import com.knf.dev.demo.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CardRepository {
 
    /*
     *  one to one Select.
     */
    @Select("SELECT cards.id, cards.email, " +
            "cards.cardNumber, cards.cardType" +
            " FROM cards WHERE cards.id = #{id}")
    @Results(value = {
        @Result(property = "email", column = "email"),
        @Result(property = "cardNumber", column = "cardNumber"),
        @Result(property = "cardType", column = "cardType"),
        @Result(property = "user", column = "email",
                one=@One(select = "getUser"))
    })
    Card selectCardById(Integer id);

    @Select("SELECT users.name, users.email FROM " +
            "users WHERE users.email = #{email}")
    User getUser(String email);

    @Select("SELECT * FROM cards")
    @Results(value = {
            @Result(property = "cardNumber", column = "cardNumber"),
            @Result(property = "cardType", column = "cardType"),
            @Result(property = "email",column = "email")
    })
    List<Card> getCards();

    @Insert("INSERT INTO cards(id,cardNumber,cardType,email)" +
            "VALUES(#{id},#{cardNumber},#{cardType},#{email})")
    int insert(Card card);







}