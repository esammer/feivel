package com.cloudera.h2.mr.util;

import java.util.HashMap;

public class DynamicMap extends HashMap<String, Object> {

  private static final long serialVersionUID = 1L;

  @SuppressWarnings("unchecked")
  public <T> T get(String key, Class<T> type) {
    if (containsKey(key)) {
      Object value;

      value = get(key);

      if (type.isAssignableFrom(value.getClass())) {
        return (T) value;
      } else {
        throw new IllegalArgumentException("Invalid type " + type + " for key "
            + key);
      }
    }

    return null;
  }

}
