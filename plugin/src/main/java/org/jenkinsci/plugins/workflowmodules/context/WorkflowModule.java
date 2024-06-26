/*
 * Copyright © 2024 VenaNocta (venanocta@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jenkinsci.plugins.workflowmodules.context;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import hudson.FilePath;
import hudson.Util;
import lombok.Setter;

public class WorkflowModule implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String id;
	private final String path;
	private final Set<String> tags = new LinkedHashSet<>() {

		private static final long serialVersionUID = 1L;

		public boolean add(String tag) {
			tag = valTag(tag);
			if (tag == null)
				return false;
			return super.add(tag);
		}

	};

	private String name;
	@Setter
	private FilePath filePath;
	private Boolean active = true;

	public WorkflowModule(final String id, final String path) {
		this.id = valId(id);
		this.path = valPath(path);
	}

	public String id() {
		return this.id;
	}

	public String path() {
		return this.path;
	}

	public FilePath filePath() {
		return this.filePath;
	}

	public String name() {
		if (this.name == null)
			return this.id;
		return this.name;
	}

	public void rename(String name) {
		this.name = valName(name);
	}

	public Set<String> tags() {
		return this.tags;
	}

	public Boolean active() {
		return this.active;
	}

	public void activate(Boolean value) {
		this.active = valActive(value);
	}

	public static String valId(String id) {
		id = Util.fixEmptyAndTrim(id);
		if (id == null) {
			throw new IllegalStateException("Module »id« is not defined!");
		}
		return id;
	}

	public static String valPath(String path) {
		path = Util.fixEmptyAndTrim(path);
		if (path == null) {
			throw new IllegalStateException("Module »path« is not defined!");
		}
		return path;
	}

	public static String valName(String name) {
		return Util.fixEmptyAndTrim(name);
	}

	public static String valTag(String name) {
		return Util.fixEmptyAndTrim(name);
	}

	public static Boolean valActive(Boolean value) {
		if (value == null)
			return false;
		return value;
	}

}
