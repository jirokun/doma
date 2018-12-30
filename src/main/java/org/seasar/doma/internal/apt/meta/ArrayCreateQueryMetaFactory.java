package org.seasar.doma.internal.apt.meta;

import static org.seasar.doma.internal.util.AssertionUtil.assertNotNull;

import java.sql.Array;
import java.util.List;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import org.seasar.doma.internal.apt.AptException;
import org.seasar.doma.internal.apt.mirror.ArrayFactoryMirror;
import org.seasar.doma.message.Message;

public class ArrayCreateQueryMetaFactory
    extends AbstractCreateQueryMetaFactory<ArrayCreateQueryMeta> {

  public ArrayCreateQueryMetaFactory(ProcessingEnvironment env) {
    super(env, Array.class);
  }

  @Override
  public QueryMeta createQueryMeta(ExecutableElement method, DaoMeta daoMeta) {
    assertNotNull(method, daoMeta);
    ArrayFactoryMirror arrayFactoryMirror = ArrayFactoryMirror.newInstance(method, env);
    if (arrayFactoryMirror == null) {
      return null;
    }
    ArrayCreateQueryMeta queryMeta = new ArrayCreateQueryMeta(method, daoMeta.getDaoElement());
    queryMeta.setArrayFactoryMirror(arrayFactoryMirror);
    queryMeta.setQueryKind(QueryKind.ARRAY_FACTORY);
    doTypeParameters(queryMeta, method, daoMeta);
    doReturnType(queryMeta, method, daoMeta);
    doParameters(queryMeta, method, daoMeta);
    doThrowTypes(queryMeta, method, daoMeta);
    return queryMeta;
  }

  @Override
  protected void doParameters(
      ArrayCreateQueryMeta queryMeta, ExecutableElement method, DaoMeta daoMeta) {
    List<? extends VariableElement> parameters = method.getParameters();
    int size = parameters.size();
    if (size != 1) {
      throw new AptException(
          Message.DOMA4002,
          env,
          method,
          new Object[] {daoMeta.getDaoElement().getQualifiedName(), method.getSimpleName()});
    }
    QueryParameterMeta parameterMeta = createParameterMeta(parameters.get(0), queryMeta);
    if (parameterMeta.getType().getKind() != TypeKind.ARRAY) {
      throw new AptException(
          Message.DOMA4076,
          env,
          parameterMeta.getElement(),
          new Object[] {daoMeta.getDaoElement().getQualifiedName(), method.getSimpleName()});
    }
    queryMeta.setElementsParameterName(parameterMeta.getName());
    queryMeta.addParameterMeta(parameterMeta);
    if (parameterMeta.isBindable()) {
      queryMeta.addBindableParameterCtType(parameterMeta.getName(), parameterMeta.getCtType());
    }
  }
}