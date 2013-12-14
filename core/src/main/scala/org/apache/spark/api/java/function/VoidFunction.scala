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

package org.apache.spark.api.java.function

/**
 * A function with no return value.
 */
// This allows Java users to write void methods without having to return Unit.
abstract class VoidFunction[T] extends Serializable {
  @throws(classOf[Exception])
  def call(t: T) : Unit
}

// VoidFunction cannot extend AbstractFunction1 (because that would force users to explicitly
// return Unit), so it is implicitly converted to a Function1[T, Unit]:
object VoidFunction {
  implicit def toFunction[T](f: VoidFunction[T]) : Function1[T, Unit] = ((x : T) => f.call(x))

  /**
   * Support for Java 8 lambdas. Accepts a lambda and returns the VoidFunction
   * instance compatible with the Java 6/7 API
   * @param f a lambda or instance of IVoidFunction
   * @return a VoidFunction instance from lambda
   */
  def of[T](f: IVoidFunction[T]): VoidFunction[T] = new VoidFunction[T]() {
    override def call(t: T): Unit = f.apply(t)
  }
}
