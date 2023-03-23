import jwt_decode from "jwt-decode";

function getRolesFromJwt(jwt) {
  if (jwt) {
    let decodedJwt = jwt_decode(jwt);
    return decodedJwt.roles;
  }
}

function hasRole(jwt, wantedRole) {
  let roles = getRolesFromJwt(jwt);
  let role = roles[0]["authority"];
  return role === wantedRole;
}

export default hasRole;
