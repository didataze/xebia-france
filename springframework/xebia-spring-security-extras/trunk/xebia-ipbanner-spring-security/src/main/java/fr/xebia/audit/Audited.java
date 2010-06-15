/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.xebia.audit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD })
public @interface Audited {

    /**
     * <p>
     * Available variables
     * </p>
     * <ul>
     * <li><code>args</code></li>
     * <li><code>invokedObject</code></li>
     * <li><code>throwned</code></li>
     * <li><code>returned</code></li>
     * </ul>
     * <p>
     * Sample :<code>"save(#{args[0]}, #{args[1]}): #{returned}"</code>
     * </p>
     */
    String message() default "";
}