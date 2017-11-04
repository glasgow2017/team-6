/*
 * This file is generated by jOOQ.
*/
package generated.db.tables;


import generated.db.Code4good;
import generated.db.Keys;
import generated.db.tables.records.QuizAnswersRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class QuizAnswers extends TableImpl<QuizAnswersRecord> {

    private static final long serialVersionUID = -999398136;

    /**
     * The reference instance of <code>code4good.quiz_answers</code>
     */
    public static final QuizAnswers QUIZ_ANSWERS = new QuizAnswers();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<QuizAnswersRecord> getRecordType() {
        return QuizAnswersRecord.class;
    }

    /**
     * The column <code>code4good.quiz_answers.id</code>.
     */
    public final TableField<QuizAnswersRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>code4good.quiz_answers.question_id</code>.
     */
    public final TableField<QuizAnswersRecord, Integer> QUESTION_ID = createField("question_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>code4good.quiz_answers.answer_text</code>.
     */
    public final TableField<QuizAnswersRecord, String> ANSWER_TEXT = createField("answer_text", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * Create a <code>code4good.quiz_answers</code> table reference
     */
    public QuizAnswers() {
        this("quiz_answers", null);
    }

    /**
     * Create an aliased <code>code4good.quiz_answers</code> table reference
     */
    public QuizAnswers(String alias) {
        this(alias, QUIZ_ANSWERS);
    }

    private QuizAnswers(String alias, Table<QuizAnswersRecord> aliased) {
        this(alias, aliased, null);
    }

    private QuizAnswers(String alias, Table<QuizAnswersRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Code4good.CODE4GOOD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<QuizAnswersRecord, Integer> getIdentity() {
        return Keys.IDENTITY_QUIZ_ANSWERS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<QuizAnswersRecord> getPrimaryKey() {
        return Keys.KEY_QUIZ_ANSWERS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<QuizAnswersRecord>> getKeys() {
        return Arrays.<UniqueKey<QuizAnswersRecord>>asList(Keys.KEY_QUIZ_ANSWERS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuizAnswers as(String alias) {
        return new QuizAnswers(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public QuizAnswers rename(String name) {
        return new QuizAnswers(name, null);
    }
}
