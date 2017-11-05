package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import generated.db.tables.QuizParticipants;
import generated.db.tables.records.QuizParticipantsRecord;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

import static generated.db.Tables.QUIZ_PARTICIPANTS;
import static org.jooq.impl.DSL.avg;
import static org.jooq.impl.DSL.countDistinct;

@Singleton
public class ParticipantsService {

    final static Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Inject
    DBService dbService;

    @Inject
    GEOIPService geoipService;

    private boolean ParticipantExists(Long pId)
    {
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);
        long num = create.selectCount().from(QUIZ_PARTICIPANTS).where(QUIZ_PARTICIPANTS.ID.eq(pId)).fetchOne(0, long.class);
        return num == 1;
    }

    // Creates a record for this participant in the DB, and returns their ID.
    private long CreateParticipant(String ip)
    {
        String ipCountry = geoipService.GetCountryOfIP(ip);
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);

        QuizParticipantsRecord participants = create.newRecord(QUIZ_PARTICIPANTS);

        participants.setAge(0);
        participants.setSex("");
        participants.setIp(ip);
        participants.setCountry(ipCountry);
        participants.setQuizAnswers("");
        participants.setPercentCorrectAnswers(0);
        participants.store();

        Long id = participants.getId();

        return id;
    }

    public long VerifyParticipantId(String participantID, String ip)
    {
        if (participantID == null || false == ParticipantExists(Long.parseLong(participantID)))
        {
            return CreateParticipant(ip);
        }
        return Long.parseLong(participantID);
    }

    public long GetNumParticipants()
    {
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);
        return create
                .selectCount()
                .from(QUIZ_PARTICIPANTS)
                .fetchOne(0, long.class);
    }

    public int GetNumCountries()
    {
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);
        Result result = create.select(countDistinct(QUIZ_PARTICIPANTS.COUNTRY))
                .from(QUIZ_PARTICIPANTS)
                .fetch();
        return (Integer) result.getValue(0, countDistinct(QUIZ_PARTICIPANTS.COUNTRY));
    }

    public int GetPercentCorrectAnswers()
    {
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);
        int result = create.select(avg(QUIZ_PARTICIPANTS.PERCENT_CORRECT_ANSWERS))
                .from(QUIZ_PARTICIPANTS)
                .fetchOne(0, int.class);
        return result;
    }

    public int GetAverageAge()
    {
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);
        int result = create.select(avg(QUIZ_PARTICIPANTS.AGE))
                .from(QUIZ_PARTICIPANTS)
                .fetchOne(0, int.class);
        return result;
    }

    public long GetNumMen()
    {
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);
        return create
                .selectCount()
                .from(QUIZ_PARTICIPANTS)
                .where(QUIZ_PARTICIPANTS.SEX.eq("M"))
                .fetchOne(0, long.class);
    }

    public long GetNumWomen()
    {
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);
        return create
                .selectCount()
                .from(QUIZ_PARTICIPANTS)
                .where(QUIZ_PARTICIPANTS.SEX.eq("F"))
                .fetchOne(0, long.class);
    }

    public long GetNumOtherGender()
    {
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);
        return create
                .selectCount()
                .from(QUIZ_PARTICIPANTS)
                .where(QUIZ_PARTICIPANTS.SEX.ne("M"))
                .and(QUIZ_PARTICIPANTS.SEX.ne("F"))
                .fetchOne(0, long.class);
    }

    public List<QuizParticipantsRecord> GetLatestQuizParticipants()
    {
        LinkedList<QuizParticipantsRecord> list = new LinkedList<QuizParticipantsRecord>();
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);

        Result<QuizParticipantsRecord> results =  create
                .selectFrom(QUIZ_PARTICIPANTS)
                .orderBy(QUIZ_PARTICIPANTS.ID.desc())
                .limit(100)
                .fetch();

        for (QuizParticipantsRecord record : results)
        {
            list.add(record);
        }

        return list;
    }

    public void DeleteAllParticipants()
    {
        DSLContext create = DSL.using(dbService.GetDataSource(), SQLDialect.MYSQL);
        create.deleteFrom(QUIZ_PARTICIPANTS).where(QUIZ_PARTICIPANTS.ID.gt(new Long(0))).execute();
    }
}
