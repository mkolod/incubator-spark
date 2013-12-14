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


import java.io.Serializable;

/**
 * A function that returns Doubles, and can be used to construct DoubleRDDs.
 */
// DoubleFunction does not extend Function because some UDF functions, like map,
// are overloaded for both Function and DoubleFunction.
public abstract class DoubleFunction<T> extends WrappedFunction1<T, Double>
  implements Serializable {
    // Intentionally left blank

    /**
     * Support for Java 8 lambdas. Accepts a lambda and returns the DoubleFunction
     * instance compatible with the Java 6/7 API
     * @param f a lambda or instance of IDoubleFunction
     * @return a DoubleFunction instance from lambda
     */
  public static <T> DoubleFunction<T> of(final IDoubleFunction<T> f) {
      return new DoubleFunction<T>() {
          @Override
          public Double call(T t) {
              return f.apply(t);
          }
      };
  }
}
