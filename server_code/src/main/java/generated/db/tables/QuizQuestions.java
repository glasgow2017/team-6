/*
 * This file is generated by jOOQ.
*/
package generated.db.tables;


import generated.db.Code4good;
import generated.db.Keys;
import generated.db.tables.records.QuizQuestionsRecord;

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
public class QuizQuestions extends TableImpl<QuizQuestionsRecord> {

    private static final long serialVersionUID = 2092332989;

    /**
     * The reference instance of <code>code4good.quiz_questions</code>
     */
    public static final QuizQuestions QUIZ_QUESTIONS = new QuizQuestions();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<QuizQuestionsRecord> getRecordType() {
        return QuizQuestionsRecord.class;
    }

    /**
     * The column <code>code4good.quiz_questions.id</code>.
     */
    public final TableField<QuizQuestionsRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>code4good.quiz_questions.question_text</code>.
     */
    public final TableField<QuizQuestionsRecord, String> QUESTION_TEXT = createField("question_text", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>code4good.quiz_questions.question_country</code>.
     */
    public final TableField<QuizQuestionsRecord, String> QUESTION_COUNTRY = createField("question_country", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>code4good.quiz_questions.ans1</code>.
     */
    public final TableField<QuizQuestionsRecord, String> ANS1 = createField("ans1", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>code4good.quiz_questions.ans2</code>.
     */
    public final TableField<QuizQuestionsRecord, String> ANS2 = createField("ans2", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>code4good.quiz_questions.ans3</code>.
     */
    public final TableField<QuizQuestionsRecord, String> ANS3 = createField("ans3", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>code4good.quiz_questions.ans4</code>.
     */
    public final TableField<QuizQuestionsRecord, String> ANS4 = createField("ans4", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>code4good.quiz_questions.correct_answer</code>.
     */
    public final TableField<QuizQuestionsRecord, Integer> CORRECT_ANSWER = createField("correct_answer", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>code4good.quiz_questions</code> table reference
     */
    public QuizQuestions() {
        this("quiz_questions", null);
    }

    /**
     * Create an aliased <code>code4good.quiz_questions</code> table reference
     */
    public QuizQuestions(String alias) {
        this(alias, QUIZ_QUESTIONS);
    }

    private QuizQuestions(String alias, Table<QuizQuestionsRecord> aliased) {
        this(alias, aliased, null);
    }

    private QuizQuestions(String alias, Table<QuizQuestionsRecord> aliased, Field<?>[] parameters) {
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
    public Identity<QuizQuestionsRecord, Integer> getIdentity() {
        return Keys.IDENTITY_QUIZ_QUESTIONS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<QuizQuestionsRecord> getPrimaryKey() {
        return Keys.KEY_QUIZ_QUESTIONS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<QuizQuestionsRecord>> getKeys() {
        return Arrays.<UniqueKey<QuizQuestionsRecord>>asList(Keys.KEY_QUIZ_QUESTIONS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QuizQuestions as(String alias) {
        return new QuizQuestions(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public QuizQuestions rename(String name) {
        return new QuizQuestions(name, null);
    }
}
