/*
 * Copyright (c) 2002-2014, Mairie de Paris
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
package fr.paris.lutece.plugins.unittree.modules.profiles.service;

import fr.paris.lutece.plugins.profiles.business.Profile;
import fr.paris.lutece.plugins.profiles.service.IProfilesService;
import fr.paris.lutece.plugins.profiles.service.ProfilesPlugin;
import fr.paris.lutece.plugins.unittree.service.unit.IUnitUserAttributeService;
import fr.paris.lutece.portal.business.user.AdminUser;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;

import java.util.List;

import javax.inject.Inject;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * ProfilesUnitUserAttributeService
 *
 */
public class ProfilesUnitUserAttributeService implements IUnitUserAttributeService
{
    @Inject
    private IProfilesService _profilesService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAddUser( int nIdUser, AdminUser currentUser, HttpServletRequest request )
    {
        //        Plugin pluginProfiles = PluginService.getPlugin( ProfilesPlugin.PLUGIN_NAME );

        // First unassign the profile from the user
        //        List<Profile> profile = _profilesService.findProfileByIdUser( nIdUser, pluginProfiles );
        //        if ( profile != null )
        //        {
        //            _profilesService.doUnassignUserFromProfile( nIdUser, profile.getKey( ), currentUser, request,
        //                request.getLocale( ), pluginProfiles );
        //        }
        _profilesService.doAssignUserToProfile( nIdUser, request, request.getLocale(  ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doModifyUser( int nIdUser, AdminUser currentUser, HttpServletRequest request )
    {
        Plugin pluginProfiles = PluginService.getPlugin( ProfilesPlugin.PLUGIN_NAME );

        // First unassigns profiles from the user
        List<Profile> listProfiles = _profilesService.findProfileByIdUser( nIdUser, pluginProfiles );

        if ( ( listProfiles != null ) && ( listProfiles.size(  ) > 0 ) )
        {
            for ( Profile profile : listProfiles )
            {
                _profilesService.doUnassignUserFromProfile( nIdUser, profile.getKey(  ), currentUser, request,
                    request.getLocale(  ), pluginProfiles );
            }
        }

        _profilesService.doAssignUserToProfile( nIdUser, request, request.getLocale(  ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doRemoveUser( int nIdUser, AdminUser currentUser, HttpServletRequest request )
    {
        // Nothing
    }
}
