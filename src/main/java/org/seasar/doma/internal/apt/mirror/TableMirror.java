package org.seasar.doma.internal.apt.mirror;

import static org.seasar.doma.internal.util.AssertionUtil.assertNotNull;

import java.util.Map;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import org.seasar.doma.Table;
import org.seasar.doma.internal.apt.AptIllegalStateException;
import org.seasar.doma.internal.apt.util.AnnotationValueUtil;
import org.seasar.doma.internal.apt.util.ElementUtil;

public class TableMirror {

  protected final AnnotationMirror annotationMirror;

  protected AnnotationValue catalog;

  protected AnnotationValue schema;

  protected AnnotationValue name;

  protected AnnotationValue quote;

  protected TableMirror(AnnotationMirror annotationMirror) {
    assertNotNull(annotationMirror);
    this.annotationMirror = annotationMirror;
  }

  public static TableMirror newInstance(TypeElement clazz, ProcessingEnvironment env) {
    assertNotNull(env);
    AnnotationMirror annotationMirror = ElementUtil.getAnnotationMirror(clazz, Table.class, env);
    if (annotationMirror == null) {
      return null;
    }
    TableMirror result = new TableMirror(annotationMirror);
    for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry :
        env.getElementUtils().getElementValuesWithDefaults(annotationMirror).entrySet()) {
      String name = entry.getKey().getSimpleName().toString();
      AnnotationValue value = entry.getValue();
      if ("catalog".equals(name)) {
        result.catalog = value;
      } else if ("schema".equals(name)) {
        result.schema = value;
      } else if ("name".equals(name)) {
        result.name = value;
      } else if ("quote".equals(name)) {
        result.quote = value;
      }
    }
    return result;
  }

  public String getCatalogValue() {
    String value = AnnotationValueUtil.toString(catalog);
    if (value == null) {
      throw new AptIllegalStateException("catalog");
    }
    return value;
  }

  public String getSchemaValue() {
    String value = AnnotationValueUtil.toString(schema);
    if (value == null) {
      throw new AptIllegalStateException("catalog");
    }
    return value;
  }

  public String getNameValue() {
    String value = AnnotationValueUtil.toString(name);
    if (value == null) {
      throw new AptIllegalStateException("name");
    }
    return value;
  }

  public boolean getQuoteValue() {
    Boolean value = AnnotationValueUtil.toBoolean(quote);
    if (value == null) {
      throw new AptIllegalStateException("quote");
    }
    return value.booleanValue();
  }
}