/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.api.java.function;

import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;

import java.io.Serializable;


/**
 * Base class for functions whose return types do not create special RDDs. PairFunction and
 * DoubleFunction are handled separately, to allow PairRDDs and DoubleRDDs to be constructed
 * when mapping RDDs of other types.
 */
public abstract class Function<T, R> extends WrappedFunction1<T, R> implements Serializable {
  public ClassTag<R> returnType() {
    return ClassTag$.MODULE$.apply(Object.class);
  }

    /**
     * Support for Java 8 lambdas. Accepts a lambda and returns the Function instance
     * compatible with the Java 6/7 API
     * @param f a lambda or instance of IFunction
     * @return a Function instance from lambda
     */
  public static <T, R> Function<T,R> of(final IFunction<T,R> f) {
      return new Function<T, R>() {
          @Override
          public R call(T t) {
              return f.apply(t);
          }
      };
  }
}

