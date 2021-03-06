package itmo.web.controller;

import itmo.web.dao.beans.DotTableBean;
import itmo.web.dao.beans.UserTableBean;
import itmo.web.dao.entities.DotEntity;
import itmo.web.dao.entities.UserEntity;
import itmo.web.model.DotAreaChecher;
import itmo.web.model.DotValidator;
import itmo.web.model.UserValidator;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@Path("/points")
public class Point {

    @EJB
    private UserTableBean userTableBean;
    @EJB
    private DotTableBean dotTableBean;

    @POST
    @Path("/add")
    public Response add(String requestBody) {
        long start = System.nanoTime();
        Response.ResponseBuilder rb = Response.ok();
        rb.header("Content-Type", "application/json;charset=UTF-8");
        rb.status(200);

        try {
            JSONObject jsonObject = new JSONObject(requestBody);
            double x = jsonObject.getDouble("coordinateX");
            double y = jsonObject.getDouble("coordinateY");
            double r = jsonObject.getDouble("radius");
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");

            if (new UserValidator().validateLoginData(username,password)) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUsername(username);
                userEntity.setPassword(password);
                if (userTableBean.login(userEntity)) {

                    if (new DotValidator().add(x,y,r)){
                        DotEntity dotEntity = new DotEntity();
                        dotEntity.setX(x);
                        dotEntity.setY(y);
                        dotEntity.setR(r);
                        dotEntity.setUser(username);
                        dotEntity.setResult(new DotAreaChecher().check(x,y,r)?"HIT":"MISS");
                        dotEntity.setCurrent_time(LocalDateTime.now());
                        dotEntity.setProcessing_time(System.nanoTime() - start);
                        dotTableBean.loadDot(dotEntity);
                        List dots = dotTableBean.getDots(userEntity);
                        String strdots = String.format("{\"updateStatus\": \"%b\", \"dots\": [",true);
                        for (Object o : dots){
                            DotEntity dot = (DotEntity) o;
                            strdots +=String.format( "{" +
                                    "\"coordinateX\": \"%f\", " +
                                    "\"coordinateY\": \"%f\", " +
                                    "\"radius\": \"%f\", " +
                                    "\"result\": \"%s\", " +
                                    "\"currentTime\": \"%s\"," +
                                    "\"processingTime\": \"%f\"" +
                                    "}",dot.getX(),dot.getY(),dot.getR(),dot.getResult(),dot.getFormatedCurrent_time(),dot.getProcessing_time());
                            if (o != dots.get(dots.size()-1)){
                                strdots+=",";
                            }
                        }

                        strdots +="]}";
                        rb.entity(strdots);
                        return rb.build();
                    } else {
                        rb.status(505);
                        //???????????? ?????????? ???? ???????????????? ??????????????????
                        rb.entity(String.format("{\"updateStatus\": \"%b\"}", false));
                        return rb.build();
                    }
                } else {
                    rb.status(501);
                    //?????????????????? ?????????? ???????????? ???? ???????????????? ?????? ???????????? ??????????????????????
                    rb.entity(String.format("{\"updateStatus\": \"%b\"}", false));
                    return rb.build();
                }
            }
            rb.status(502);
            //???????????? ???????????????? ???? ???????????????? ??????????????????
            rb.entity(String.format("{\"updateStatus\": \"%b\"}", false));
            return rb.build();
        } catch (JSONException e){
            rb.status(503);
            //???????????? ???? ???????????????? ??????????????????????
            rb.entity(String.format("{\"updateStatus\": \"%b\"}", false));
            return rb.build();
        }
    }

    @POST
    @Path("/observe")
    public Response observe(String requestBody) {
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
                if (userTableBean.login(userEntity)) {
                    List dots = dotTableBean.getDots(userEntity);
                    String strdots = String.format("{\"updateStatus\": \"%b\", \"dots\": [",true);
                    for (Object o : dots){
                        DotEntity dot = (DotEntity) o;
                        strdots +=String.format( "{" +
                                "\"coordinateX\": \"%f\", " +
                                "\"coordinateY\": \"%f\", " +
                                "\"radius\": \"%f\", " +
                                "\"result\": \"%s\", " +
                                "\"currentTime\": \"%s\"," +
                                "\"processingTime\": \"%f\"" +
                                "}",dot.getX(),dot.getY(),dot.getR(),dot.getResult(),dot.getFormatedCurrent_time(),dot.getProcessing_time());
                        if (o != dots.get(dots.size()-1)){
                            strdots+=",";
                        }
                    }
                    strdots +="]}";
                    rb.entity(strdots);
                    return rb.build();
                } else {
                    rb.status(501);
                    //?????????????????? ?????????? ???????????? ???? ???????????????? ?????? ???????????? ??????????????????????
                    rb.entity(String.format("{\"updateStatus\": \"%b\"}", false));
                    return rb.build();
                }
            }
            rb.status(502);
            //???????????? ???????????????? ???? ???????????????? ??????????????????
            rb.entity(String.format("{\"updateStatus\": \"%b\"}", false));
            return rb.build();
        } catch (JSONException e){
            rb.status(503);
            //???????????? ???? ???????????????? ??????????????????????
            rb.entity(String.format("{\"updateStatus\": \"%b\"}", false));
            return rb.build();
        }
    }

    @POST
    @Path("/reset")
    public Response reset(String requestBody) {

        Response.ResponseBuilder rb = Response.ok();
        rb.header("Content-Type", "application/json;charset=UTF-8");
        rb.status(200);

        try {
            JSONObject jsonObject = new JSONObject(requestBody);
            String  username = jsonObject.getString("username");
            String password = jsonObject.getString("password");

            if (new UserValidator().validateLoginData(username,password)) {

                UserEntity userEntity = new UserEntity();
                userEntity.setUsername(username);
                userEntity.setPassword(password);
                if (userTableBean.login(userEntity)) {

                    dotTableBean.resetDots(userEntity);
                    rb.entity(String.format("{\"updateStatus\": \"%b\"}", true));
                    return rb.build();
                } else {
                    rb.status(501);
                    //?????????????????? ?????????? ???????????? ???? ???????????????? ?????? ???????????? ??????????????????????
                    rb.entity(String.format("{\"updateStatus\": \"%b\"}", false));
                    return rb.build();
                }
            }
            rb.status(502);
            //???????????? ???? ???????????????? ??????????????????
            rb.entity(String.format("{\"updateStatus\": \"%b\"}", false));
            return rb.build();
        } catch (JSONException e){
            rb.status(503);
            //???????????? ???? ???????????????? ??????????????????????
            rb.entity(String.format("{\"updateStatus\": \"%b\"}", false));
            return rb.build();
        }
    }
}
