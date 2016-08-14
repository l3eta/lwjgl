/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lwjgl.util;

import java.util.Stack;

/**
 *
 * @author l3eta
 * @date Aug 8, 2016
 * @time 1:26:53 PM
 */
public class Profiler {

    private static class Profile {

        public String name;
        public long start;
        public long end;

        public float getDifference() {
            return (end - start) / 1000000000.0f;
        }

        public long getSeconds() {
            return (long) (getDifference() * 1000L);
        }
    }

    private static Stack<Profile> profiles = new Stack<>();

    public static void push(String name) {
        Profile profile = new Profile();
        profile.name = name;
        profile.start = System.nanoTime();
        profiles.push(profile);
    }

    public static void pop() {
        Profile profile = profiles.pop();
        profile.end = System.nanoTime();
        String msg = Line.format("Profile: @0 took @1 seconds to complete", profile.name, profile.getSeconds());
        System.err.println("[Profiler] " + msg);

    }

    private static class Line {

        public static String format(String val, Object... args) {
            for (int i = 0; i < args.length; i++) {
                val = val.replace("@" + i, String.valueOf(args[i]));
            }
            return val;
        }
    }
}
