package com.bit.common.auth;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class ShiroSessionListenser implements SessionListener {

    public void onStart(Session session) {
        System.out.println("ShiroSessionListenser:onStart starting session -> " + session.getId() + " : " + session.toString() );
    }

    public void onStop(Session session) {
        System.out.println("ShiroSessionListenser:onStop stoping session -> " + session.getId() + " : " + session.toString() );
    }

    public void onExpiration(Session session) {
        System.out.println("ShiroSessionListenser:onExpiration expiring session -> " + session.getId() + " : " + session.toString() );
    }

}