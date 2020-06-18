/**
 * This file is part of Graylog.
 *
 * Graylog is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog2.storage;

import com.google.inject.Provider;
import org.graylog2.plugin.Version;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

public class VersionSpecificProvider<T> implements Provider<T> {
    private final T instance;

    @Inject
    public VersionSpecificProvider(@Named("elasticsearch_version") String elasticsearchMajorVersion, Map<Version, T> pluginBindings) {
        final Version elasticsearchVersion = constructElasticsearchVersion(elasticsearchMajorVersion);
        this.instance = pluginBindings.get(elasticsearchVersion);
    }

    private static Version constructElasticsearchVersion(String elasticsearchVersion) {
        final int majorVersion = Integer.parseInt(elasticsearchVersion);
        return Version.from(majorVersion, 0, 0);
    }

    @Override
    public T get() {
        return this.instance;
    }
}