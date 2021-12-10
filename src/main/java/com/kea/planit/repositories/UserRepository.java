package com.kea.planit.repositories;

import com.kea.planit.models.UserModel;
import com.kea.planit.utilities.DBconnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//Author: Jonatan Segal
@Repository
public class UserRepository {

    private static UserRepository singe_UR = null;
    private Connection conn = DBconnector.getConnection();
    private static ArrayList<UserModel> userModels = new ArrayList<>();
    public static UserRepository getInstance(){
        if(singe_UR == null){
            singe_UR = new UserRepository();
        }
        return singe_UR;
    }

    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public Connection getConn(){
        return conn;
    }


    public ArrayList<UserModel> getAllUsers(){
        PreparedStatement ppst=null;
        try{
            ppst = conn.prepareStatement("SELECT * FROM planit.users");
            ResultSet rs = ppst.executeQuery();
            while(rs.next()){
                UserModel rsUser = new UserModel(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        passwordEncoder.encode(rs.getString(4))
                );
                userModels.add(rsUser);
            }

        }catch (SQLException e){
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
        }
        return userModels;
    }

    public void addUser(UserModel u){
        try {
            PreparedStatement ppst = UserRepository.getInstance().getConn().prepareStatement
                    ("INSERT INTO planit.users (username,useremail,userpassword) VALUES (?,?,?)");
            ppst.setString(1,u.getName());
            ppst.setString(2,u.getEmail());
            ppst.setString(3,u.getPassword());
            ppst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something is wrong");
            System.out.println(e.getMessage());
        }
    }

    public UserModel findByEmail(String email){
        for(UserModel u: getAllUsers()){
            System.out.println("Do I get here?");
            System.out.println(email+" "+u.getEmail());
            System.out.println(u.getEmail().contains(email)?true:false);
            if(u.getEmail().equals(email)){
                System.out.println("I am here");
                return u;
            }
        }
        return null;
    }
}
