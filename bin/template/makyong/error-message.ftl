<#assign hasFieldErrors = parameters.name?? && fieldErrors?? && fieldErrors[parameters.name]??/>
<#if hasFieldErrors>
<#list fieldErrors[parameters.name] as error>
   <span class="errorMessage" errorFor="${parameters.id}">${error?html}</span><#t/>
</#list>
</#if>


