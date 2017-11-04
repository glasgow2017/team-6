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
@Table(name = "quiz_answers", schema = "code4good")
public class QuizAnswers implements Serializable {

    private static final long serialVersionUID = -1094568442;

    private final Integer id;
    private final Integer questionId;
    private final String  answerText;

    public QuizAnswers(QuizAnswers value) {
        this.id = value.id;
        this.questionId = value.questionId;
        this.answerText = value.answerText;
    }

    public QuizAnswers(
        Integer id,
        Integer questionId,
        String  answerText
    ) {
        this.id = id;
        this.questionId = questionId;
        this.answerText = answerText;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, precision = 10)
    @NotNull
    public Integer getId() {
        return this.id;
    }

    @Column(name = "question_id", nullable = false, precision = 10)
    @NotNull
    public Integer getQuestionId() {
        return this.questionId;
    }

    @Column(name = "answer_text", nullable = false, length = 16777215)
    @NotNull
    @Size(max = 16777215)
    public String getAnswerText() {
        return this.answerText;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("QuizAnswers (");

        sb.append(id);
        sb.append(", ").append(questionId);
        sb.append(", ").append(answerText);

        sb.append(")");
        return sb.toString();
    }
}
