package org.seasar.doma.internal.apt.meta;

import static org.seasar.doma.internal.util.AssertionUtil.assertNotNull;

import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import org.seasar.doma.internal.apt.AptException;
import org.seasar.doma.message.Message;

public abstract class AbstractCreateQueryMetaFactory<M extends AbstractCreateQueryMeta>
    extends AbstractQueryMetaFactory<M> {

  protected final Class<?> returnClass;

  protected AbstractCreateQueryMetaFactory(ProcessingEnvironment env, Class<?> returnClass) {
    super(env);
    assertNotNull(returnClass);
    this.returnClass = returnClass;
  }

  @Override
  protected void doReturnType(M queryMeta, ExecutableElement method, DaoMeta daoMeta) {
    QueryReturnMeta resultMeta = createReturnMeta(queryMeta);
    queryMeta.setReturnMeta(resultMeta);
    if (!returnClass.getName().equals(resultMeta.getCtType().getQualifiedName())) {
      throw new AptException(
          Message.DOMA4097,
          env,
          method,
          new Object[] {
            returnClass.getName(),
            daoMeta.getDaoElement().getQualifiedName(),
            method.getSimpleName()
          });
    }
  }

  @Override
  protected void doParameters(M queryMeta, ExecutableElement method, DaoMeta daoMeta) {
    List<? extends VariableElement> params = method.getParameters();
    int size = params.size();
    if (size != 0) {
      throw new AptException(
          Message.DOMA4078,
          env,
          method,
          new Object[] {daoMeta.getDaoElement().getQualifiedName(), method.getSimpleName()});
    }
  }
}