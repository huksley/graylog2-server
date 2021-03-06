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
package org.graylog.plugins.views.search.engine;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import org.graylog2.plugin.indexer.searches.timeranges.AbsoluteRange;
import org.joda.time.DateTime;

import static org.joda.time.DateTimeZone.UTC;

@AutoValue
@JsonDeserialize(builder = QueryExecutionStats.Builder.class)
public abstract class QueryExecutionStats {
    @JsonProperty("duration")
    public abstract long duration();

    @JsonProperty("timestamp")
    public abstract DateTime timestamp();

    @JsonProperty("effective_timerange")
    public abstract AbsoluteRange effectiveTimeRange();

    public static QueryExecutionStats empty() {
        return builder().build();
    }

    public static Builder builderWithCurrentTime() {
        return builder().timestamp(DateTime.now(UTC));
    }

    public static Builder builder() {
        return Builder.create();
    }

    public abstract Builder toBuilder();

    @AutoValue.Builder
    public static abstract class Builder {
        @JsonCreator
        public static Builder create() {
            return new AutoValue_QueryExecutionStats.Builder()
                    .timestamp(DateTime.now(UTC))
                    .effectiveTimeRange(AbsoluteRange.create(DateTime.now(UTC), DateTime.now(UTC)))
                    .duration(0L);
        }

        @JsonProperty("duration")
        public abstract Builder duration(long duration);

        @JsonProperty("timestamp")
        public abstract Builder timestamp(DateTime timestamp);

        @JsonProperty("effective_timerange")
        public abstract Builder effectiveTimeRange(AbsoluteRange effectiveTimeRange);

        public abstract QueryExecutionStats build();
    }
}
