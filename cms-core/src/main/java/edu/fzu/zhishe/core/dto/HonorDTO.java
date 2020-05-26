package edu.fzu.zhishe.core.dto;

public class HonorDTO {

    /**
     * grade : 冒泡
     * score : 6
     * precentage : 30
     */

    private String grade;
    private Integer score;
    private Integer percentage;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
