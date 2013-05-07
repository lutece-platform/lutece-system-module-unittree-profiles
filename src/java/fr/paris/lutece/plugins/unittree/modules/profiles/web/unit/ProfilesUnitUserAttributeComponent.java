/*
 * Copyright (c) 2002-2013, Mairie de Paris
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
package fr.paris.lutece.plugins.unittree.modules.profiles.web.unit;

import fr.paris.lutece.plugins.profiles.service.ProfilesPlugin;
import fr.paris.lutece.plugins.unittree.web.unit.IUnitUserAttributeComponent;
import fr.paris.lutece.portal.business.user.attribute.IAttribute;
import fr.paris.lutece.portal.service.user.attribute.AdminUserFieldService;
import fr.paris.lutece.portal.service.user.attribute.AttributeService;

import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * ProfilesUnitUserAttributeComponent
 *
 */
public class ProfilesUnitUserAttributeComponent implements IUnitUserAttributeComponent
{
    private static final String NAME = "Profiles unit user attribute";

    // PARAMETERS
    private static final String PARAMETER_ID_USER = "idUser";

    // MARKS
    private static final String MARK_MAP_LIST_ATTRIBUTE_DEFAULT_VALUES = "map_list_attribute_default_values";
    private static final String MARK_ATTRIBUTES_LIST = "attributes_list";
    private static final String MARK_LOCALE = "locale";

    // TEMPLATES
    private static final String TEMPLATE_ATTRIBUTE_COMPONENT = "modules/profiles/profiles_unit_user_attribute.html";

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillModel( HttpServletRequest request, Map<String, Object> model )
    {
        List<IAttribute> listAttributes = AttributeService.getInstance(  )
                                                          .getPluginAttributesWithFields( ProfilesPlugin.PLUGIN_NAME,
                request.getLocale(  ) );
        String strIdUser = request.getParameter( PARAMETER_ID_USER );

        if ( StringUtils.isNotBlank( strIdUser ) && StringUtils.isNumeric( strIdUser ) && ( listAttributes != null ) &&
                !listAttributes.isEmpty(  ) )
        {
            int nIdUser = Integer.parseInt( strIdUser );
            Map<String, Object> map = AdminUserFieldService.getAdminUserFields( listAttributes, nIdUser,
                    request.getLocale(  ) );
            model.put( MARK_MAP_LIST_ATTRIBUTE_DEFAULT_VALUES, map );
        }

        model.put( MARK_ATTRIBUTES_LIST, listAttributes );
        model.put( MARK_LOCALE, request.getLocale(  ) );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName(  )
    {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplate(  )
    {
        return TEMPLATE_ATTRIBUTE_COMPONENT;
    }
}
