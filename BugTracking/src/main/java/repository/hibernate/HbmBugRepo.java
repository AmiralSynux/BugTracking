package repository.hibernate;

import domain.Bug;
import domain.BugStatus;
import domain.Programmer;
import domain.Tester;
import domain.validators.Validator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.IBugRepository;

import java.util.ArrayList;
import java.util.List;

public class HbmBugRepo extends HbmRepo<Long, Bug> implements IBugRepository {
    public HbmBugRepo(SessionFactory sessionFactory, Validator<Bug> validator) {
        super(sessionFactory, validator);
    }

    @Override
    protected Bug getQuery(Session session, Long aLong) {
        return session.createQuery("from Bug where id = :tid",
                Bug.class).setParameter("tid",aLong).uniqueResult();
    }

    @Override
    protected List<Bug> getAllQuery(Session session) {
        return session.createQuery("from Bug as b where b.status!=:st order by b.name",
                Bug.class).setParameter("st", BugStatus.SOLVED).list();
    }

}
