package com.xiaoshan.polly.followme.domain;

import java.util.List;

/**
 * Created by Administrator on 2015/11/25.
 */
public class Bean {

    /**
     * name : 王五
     * gender : man
     * age : 15
     * height : 140cm
     * addr : {"province":"fujian","city":"quanzhou","code":"300000"}
     * hobby : [{"name":"billiards","code":"1"},{"name":"computerGame","code":"2"}]
     */

    private String name;
    private String gender;
    private int age;
    private String height;
    /**
     * province : fujian
     * city : quanzhou
     * code : 300000
     */

    private AddrEntity addr;
    /**
     * name : billiards
     * code : 1
     */

    private List<HobbyEntity> hobby;

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setAddr(AddrEntity addr) {
        this.addr = addr;
    }

    public void setHobby(List<HobbyEntity> hobby) {
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public AddrEntity getAddr() {
        return addr;
    }

    public List<HobbyEntity> getHobby() {
        return hobby;
    }

    public static class AddrEntity {
        private String province;
        private String city;
        private String code;

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getCode() {
            return code;
        }
    }

    public static class HobbyEntity {
        private String name;
        private String code;

        public void setName(String name) {
            this.name = name;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }
}
