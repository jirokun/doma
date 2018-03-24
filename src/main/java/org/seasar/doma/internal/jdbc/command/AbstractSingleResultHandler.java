package org.seasar.doma.internal.jdbc.command;

import static org.seasar.doma.internal.util.AssertionUtil.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.seasar.doma.jdbc.NonUniqueResultException;
import org.seasar.doma.jdbc.Sql;
import org.seasar.doma.jdbc.command.ResultSetHandler;
import org.seasar.doma.jdbc.query.SelectQuery;

public abstract class AbstractSingleResultHandler<RESULT> implements ResultSetHandler<RESULT> {

    protected final ResultSetHandler<RESULT> handler;

    public AbstractSingleResultHandler(ResultSetHandler<RESULT> handler) {
        assertNotNull(handler);
        this.handler = handler;
    }

    @Override
    public Supplier<RESULT> handle(ResultSet resultSet, SelectQuery query,
                                   Consumer<ResultSetState> stateChecker) throws SQLException {
        return handler.handle(resultSet, query, stateChecker.andThen((resultSetState) -> {
            if (resultSetState == ResultSetState.MULTIPLE_RESULTS) {
                Sql<?> sql = query.getSql();
                throw new NonUniqueResultException(query.getConfig().getExceptionSqlLogType(), sql);
            }
        }));
    }
}
