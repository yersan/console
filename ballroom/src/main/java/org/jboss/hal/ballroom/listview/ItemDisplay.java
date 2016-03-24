/*
 * Copyright 2015-2016 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.hal.ballroom.listview;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import elemental.dom.Element;
import org.jboss.gwt.elemento.core.HasElements;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.hal.ballroom.HasTitle;

import java.util.ArrayList;
import java.util.List;

/**
 * Controls the layout of a list view item.
 *
 * @author Harald Pehl
 */
public interface ItemDisplay<T> extends IsElement, HasTitle {

    /**
     * An unique id for this item
     *
     * @return an id based on {@link #getTitle()}
     */
    default String getId() {
        Iterable<String> parts = Splitter.on(CharMatcher.WHITESPACE).omitEmptyStrings().trimResults().split(getTitle());
        return FluentIterable.from(parts).transform(String::toLowerCase).join(Joiner.on('-'));
    }

    default boolean stacked() {
        return false;
    }

    default Element status() {
        return null;
    }

    default HasElements getTitleElements() {
        return null;
    }

    String getDescription();

    default HasElements getDescriptionElements() {
        return null;
    }

    default HasElements getAdditionalInfo() {
        return null;
    }

    /**
     * Defines the action(s) available for the item.
     *
     * @return an empty map by default.
     */
    default List<ItemAction<T>> actions() {
        return new ArrayList<>();
    }

    /**
     * If this method returns an element != {@code null} this element is used to display the item.
     *
     * @return {@code null} by default
     */
    default Element asElement() {
        return null;
    }
}
