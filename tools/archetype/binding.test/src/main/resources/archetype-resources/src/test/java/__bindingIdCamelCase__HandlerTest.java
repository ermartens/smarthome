#set( $dt = $package.getClass().forName("java.util.Date").newInstance() )
#set( $year = $dt.getYear() + 1900 )
#if( $vendorName == "Eclipse.org/SmartHome" )
    #set( $copyright = "Contributors to the Eclipse Foundation" )
#else
    #set( $copyright = "by the respective copyright holders." )
#end
/**
 * Copyright (c) ${startYear},${year} ${copyright}
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package ${package};

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import ${package}.internal.${bindingIdCamelCase}Handler;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusInfo;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerCallback;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

/**
 * Test cases for {@link ${bindingIdCamelCase}Handler}. The tests provide mocks for supporting entities using Mockito.
 *
 * @author ${author} - Initial contribution
 */
public class ${bindingIdCamelCase}HandlerTest {

    private ThingHandler handler;

    @Mock
    private ThingHandlerCallback callback;

    @Mock
    private Thing thing;

    @Before
    public void setUp() {
        initMocks(this);
        handler = new ${bindingIdCamelCase}Handler(thing);
        handler.setCallback(callback);
    }

    @Test
    public void initializeShouldCallTheCallback() {
        // mock the function to prevent null exception.
        when(thing.getConfiguration()).thenReturn(new demo1Configuration());
        // we expect the handler#initialize method to call the callback during execution and
        // pass it the thing and a ThingStatusInfo object containing the ThingStatus of the thing.
        handler.initialize();

        // the argument captor will capture the argument of type ThingStatusInfo given to the
        // callback#statusUpdated method.
        ArgumentCaptor<ThingStatusInfo> statusInfoCaptor = ArgumentCaptor.forClass(ThingStatusInfo.class);

        // verify the interaction with the callback and capture the ThingStatusInfo argument:
        verify(callback).statusUpdated(eq(thing), statusInfoCaptor.capture());
        // assert that the ThingStatusInfo given to the callback was build with the ONLINE status:
        ThingStatusInfo thingStatusInfo = statusInfoCaptor.getValue();
        assertThat(thingStatusInfo.getStatus(), is(equalTo(ThingStatus.ONLINE)));
    }
}
