package com.socialweb.query;

import com.socialweb.domain.UserData;
import com.socialweb.form.SearchForm;
import java.util.List;


public interface QueryBuilder {
    public List<UserData> buildSearchQuery(SearchForm searchForm);
}
