  <g:form controller="auth" action="signIn" method="POST">
    <input type="hidden" name="targetUri" value="${targetUri}" />
    <table>
      <tbody>
        <tr>
          <td>Benutzerkennung:&nbsp;</td>
          <td><input type="text" name="username" value="${username}" /></td>
        </tr>
        <tr>
          <td>Passwort:&nbsp;</td>
          <td><input type="password" name="password" value="" /></td>
        </tr>
        <tr>
          <td>Login merken?:&nbsp;</td>
          <td><g:checkBox name="rememberMe" value="${rememberMe}" /></td>
        </tr>
        <tr>
          <td />
          <td><input type="submit" value="Login" /></td>
        </tr>
      </tbody>
    </table>
  </g:form>
