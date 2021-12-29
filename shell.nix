{ pkgs ? import ./nix/nixpkgs-pinned.nix }:

pkgs.mkShell {
  name = "covid-shell";
  version = "0.0.1";

  buildInputs = with pkgs; [
    jetbrains.idea-ultimate
    tomcat9
    tomcat_mysql_jdbc
    maven
  ];
}
