package com.example.myrecylerview;

public class Model {


        private String course_name;
        private String course_rating;
        private String course_image;

        // Constructor
        public  Model(String course_name, String course_rating, String course_image) {
            this.course_name = course_name;
            this.course_rating = course_rating;
            this.course_image = course_image;
        }

        // Getter and Setter
        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getCourse_rating() {
            return course_rating;
        }

        public void setCourse_rating(String course_rating) {
            this.course_rating = course_rating;
        }

        public String getCourse_image() {
            return course_image;
        }

        public void setCourse_image(String course_image) {
            this.course_image = course_image;
        }
    }


