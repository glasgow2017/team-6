/*
 * This file is generated by jOOQ.
*/
package generated.db.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "quiz_participants", schema = "code4good")
public class QuizParticipants implements Serializable {

    private static final long serialVersionUID = 1582672091;

    private final Long    id;
    private final Integer age;
    private final String  sex;
    private final String  ip;
    private final String  country;
    private final String  quizAnswers;
    private final Integer percentCorrectAnswers;

    public QuizParticipants(QuizParticipants value) {
        this.id = value.id;
        this.age = value.age;
        this.sex = value.sex;
        this.ip = value.ip;
        this.country = value.country;
        this.quizAnswers = value.quizAnswers;
        this.percentCorrectAnswers = value.percentCorrectAnswers;
    }

    public QuizParticipants(
        Long    id,
        Integer age,
        String  sex,
        String  ip,
        String  country,
        String  quizAnswers,
        Integer percentCorrectAnswers
    ) {
        this.id = id;
        this.age = age;
        this.sex = sex;
        this.ip = ip;
        this.country = country;
        this.quizAnswers = quizAnswers;
        this.percentCorrectAnswers = percentCorrectAnswers;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, precision = 19)
    @NotNull
    public Long getId() {
        return this.id;
    }

    @Column(name = "age", nullable = false, precision = 10)
    @NotNull
    public Integer getAge() {
        return this.age;
    }

    @Column(name = "sex", nullable = false, length = 45)
    @NotNull
    @Size(max = 45)
    public String getSex() {
        return this.sex;
    }

    @Column(name = "ip", nullable = false, length = 45)
    @NotNull
    @Size(max = 45)
    public String getIp() {
        return this.ip;
    }

    @Column(name = "country", nullable = false, length = 255)
    @NotNull
    @Size(max = 255)
    public String getCountry() {
        return this.country;
    }

    @Column(name = "quiz_answers", nullable = false, length = 65535)
    @NotNull
    @Size(max = 65535)
    public String getQuizAnswers() {
        return this.quizAnswers;
    }

    @Column(name = "percent_correct_answers", nullable = false, precision = 10)
    @NotNull
    public Integer getPercentCorrectAnswers() {
        return this.percentCorrectAnswers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("QuizParticipants (");

        sb.append(id);
        sb.append(", ").append(age);
        sb.append(", ").append(sex);
        sb.append(", ").append(ip);
        sb.append(", ").append(country);
        sb.append(", ").append(quizAnswers);
        sb.append(", ").append(percentCorrectAnswers);

        sb.append(")");
        return sb.toString();
    }
}
