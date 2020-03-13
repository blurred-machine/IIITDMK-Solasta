package com.iiitdmk.solasta.data;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class TeamData {

    private String name;
    private String email;
    private String role;
    private String imageUrl;
    private String linkedin;
    private String github;

    public TeamData(){}

    public TeamData(String name,String email,String role,String imageUrl) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.imageUrl = imageUrl;
    }


    public TeamData(String name,String email,String role,String imageUrl, String linkedin, String github) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.imageUrl = imageUrl;
        this.linkedin = linkedin;
        this.github = github;
    }

    public static Comparator<TeamData> teamMemberNameComparator = new Comparator<TeamData>() {
        public int compare(TeamData s1, TeamData s2) {
            String StudentName1 = s1.getName().toUpperCase();
            String StudentName2 = s2.getName().toUpperCase();
            return StudentName1.compareTo(StudentName2);
        }};




    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getGithub() {
        return github;
    }

    public String getRole() {
        return role;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
