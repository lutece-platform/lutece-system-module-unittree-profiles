/*
 * Copyright (c) 2002-2026, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.unittree.modules.profiles;

import fr.paris.lutece.plugins.unittree.modules.profiles.service.ProfilesUnitUserAttributeService;
import fr.paris.lutece.plugins.unittree.modules.profiles.web.unit.ProfilesUnitUserAttributeComponent;
import fr.paris.lutece.plugins.unittree.service.unit.IUnitUserAttributeService;
import fr.paris.lutece.plugins.unittree.service.unit.UnitUserAttributeManager;
import fr.paris.lutece.plugins.unittree.web.unit.IUnitUserAttributeComponent;
import fr.paris.lutece.test.LuteceTestCase;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Checks that unittree discovers the profiles user attribute beans through CDI.
 */
public class ProfilesUnitUserAttributeDiscoveryTest extends LuteceTestCase
{
    /**
     * The profiles attribute component is listed among the unit user attribute components.
     */
    @Test
    public void testComponentIsDiscovered( )
    {
        List<IUnitUserAttributeComponent> listComponents = UnitUserAttributeManager.getListUnitUserAttributeComponents( );

        assertTrue( listComponents.stream( ).anyMatch( c -> c instanceof ProfilesUnitUserAttributeComponent ) );
    }

    /**
     * The profiles attribute service is listed among the unit user attribute services.
     */
    @Test
    public void testServiceIsDiscovered( )
    {
        List<IUnitUserAttributeService> listServices = UnitUserAttributeManager.getListUnitUserAttributeService( );

        assertTrue( listServices.stream( ).anyMatch( s -> s instanceof ProfilesUnitUserAttributeService ) );
    }
}
