package com.cyq.retrofit.bean;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/2/1 14:42
 */
public class PersonBean{

    /**
     * code : 200
     * description : success
     * data : {"phone":"185634523402","name":"张三","id":10128762,"age":28,"email":"989765987@qq.com"}
     */

    private int code;
    private String description;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * phone : 185634523402
         * name : 张三
         * id : 10128762
         * age : 28
         * email : 989765987@qq.com
         */

        private String phone;
        private String name;
        private int id;
        private int age;
        private String email;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
