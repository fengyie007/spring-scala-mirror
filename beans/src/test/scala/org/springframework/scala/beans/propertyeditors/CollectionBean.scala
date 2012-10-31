/*
 * Copyright 2011-2012 the original author or authors.
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

package org.springframework.scala.beans.propertyeditors

import reflect.BeanProperty
import collection.mutable.ArrayBuffer

class CollectionBean {

	@BeanProperty
	var seq: Seq[String] = Seq()

	@BeanProperty
	var list: List[String] = List()

	@BeanProperty
	var vector: Vector[String] = Vector()

	@BeanProperty
	var arrayBuffer1: ArrayBuffer[String] = ArrayBuffer()

	@BeanProperty
	var arrayBuffer2: ArrayBuffer[String] = ArrayBuffer()

}
