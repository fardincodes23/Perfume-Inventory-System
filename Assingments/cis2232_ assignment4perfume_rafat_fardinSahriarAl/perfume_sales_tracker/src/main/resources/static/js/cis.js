// For demo to fit into DataTables site builder...
$(document).ready(function () {
    $('#theData').dataTable();
    $('#theData')
            .removeClass('display')
            .addClass('table table-striped table-bordered');

    //alert("Ripple is used to animate the buttons a bit");
    mdc.ripple.MDCRipple.attachTo(document.querySelector('.foo-button'));

});