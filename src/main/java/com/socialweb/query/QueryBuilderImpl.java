package com.socialweb.query;

import com.mysema.query.QueryModifiers;
import com.mysema.query.hql.HQLQuery;
import com.mysema.query.hql.hibernate.HibernateQuery;
import com.socialweb.domain.QEmailUserData;
import com.socialweb.domain.QUserData;
import com.socialweb.domain.UserData;
import com.socialweb.form.SearchForm;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryBuilderImpl implements QueryBuilder {

    @Autowired
    private SessionFactory sessionFactory;

    private final QUserData userData = QUserData.userData;
    private final QEmailUserData emailUserData = QEmailUserData.emailUserData;

    @Override
    public List<UserData> buildSearchQuery(SearchForm searchForm) {
        HQLQuery query = new HibernateQuery(sessionFactory.getCurrentSession())
                .from(userData).innerJoin(userData.emails, emailUserData);

        query = parseLike(query, searchForm);
        query = parseDate(query, searchForm);
        query = parseEquals(query, searchForm);
        QueryModifiers qm = new QueryModifiers(20L, 0L);

        return query.restrict(qm).listDistinct(userData);

    }

    private HQLQuery parseLike(HQLQuery query, SearchForm searchForm) {
        if (!searchForm.getName().equals("")) {
            query.where(userData.name.like("%" + searchForm.getName() + "%"));
        }
        if (!searchForm.getSurname().equals("")) {
            query.where(userData.surname.like("%" + searchForm.getSurname() + "%"));
        }
        if (!searchForm.getHometown().equals("")) {
            query.where(userData.hometown.like("%" + searchForm.getHometown() + "%"));
        }
        return query;
    }

    private HQLQuery parseDate(HQLQuery query, SearchForm searchForm) {
        if (searchForm.getAgeTo() > 0) {
            Calendar dateFrom = Calendar.getInstance();
            Calendar dateTo = Calendar.getInstance();

            dateFrom.setTime(new Date());
            dateFrom.add(Calendar.YEAR, -(searchForm.getAgeTo() + 1));

            dateTo.setTime(new Date());
            dateTo.add(Calendar.YEAR, -searchForm.getAgeFrom());

            return query.where(userData.birth.between(dateFrom.getTime(), dateTo.getTime()));
        }
        return query;
    }

    private HQLQuery parseEquals(HQLQuery query, SearchForm searchForm) {
        if (!searchForm.getEmail().equals("")) {
            return query.where(emailUserData.email.equalsIgnoreCase(searchForm.getEmail()));
        }

        return query;
    }
}
