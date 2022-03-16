package itmo.web.controller;

import itmo.web.dao.beans.DotTableBean;
import itmo.web.dao.beans.UserTableBean;
import itmo.web.dao.entities.UserEntity;
import itmo.web.model.UserValidator;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class User {

    @EJB
    private UserTableBean userTableBean;
    @EJB
    private DotTableBean dotTableBean;

    @POST
    @Path("/signup")
    public Response singUp(String requestBody) {
        Response.ResponseBuilder rb = Response.ok();
        rb.header("Content-Type", "application/json;charset=UTF-8");
        rb.status(200);
        try {
            JSONObject jsonObject = new JSONObject(requestBody);
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");

            if (new UserValidator().validateSignUpData(username,password)) {

                UserEntity userEntity = new UserEntity();
                userEntity.setUsername(username);
                userEntity.setPassword(password);

                if (userTableBean.register(userEntity)){

                    rb.entity(String.format("{\"authStatus\": \"%b\"}", true));
                    return rb.build();
                } else {
                    rb.status(501);
                    //неудалось добавить в бд
                    rb.entity(String.format("{\"authStatus\": \"%b\"}", false));
                    return rb.build();
                }
            }
            rb.status(502);
            //данные не проходят валидацию
            rb.entity(String.format("{\"authStatus\": \"%b\"}", false));
            return rb.build();
        } catch (JSONException e){;
            rb.status(503);
            //данные не проходят конвертацию
            rb.entity(String.format("{\"authStatus\": \"%b\"}", false));
            return rb.build();
        }
    }

    @POST
    @Path("/login")
    public Response login(String requestBody) {
        Response.ResponseBuilder rb = Response.ok();
        rb.header("Content-Type", "application/json;charset=UTF-8");
        rb.status(200);

        try {
            JSONObject jsonObject = new JSONObject(requestBody);
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            if (new UserValidator().validateLoginData(username,password)) {

                UserEntity userEntity = new UserEntity();
                userEntity.setUsername(username);
                userEntity.setPassword(password);

                if (userTableBean.login(userEntity)){

                    rb.entity(String.format("{\"authStatus\": \"%b\"}", true));
                    return rb.build();
                } else {
                    rb.status(501);
                    //неудалось найти запись
                    rb.entity(String.format("{\"authStatus\": \"%b\"}", false));
                    return rb.build();
                }
            }
            rb.status(502);
            //данные не проходят валидацию
            rb.entity(String.format("{\"authStatus\": \"%b\"}", false));
            return rb.build();
        } catch (JSONException e){
            rb.status(503);
            //данные не проходят конвертацию
            rb.entity(String.format("{\"authStatus\": \"%b\"}", false));
            return rb.build();
        }
    }

}
